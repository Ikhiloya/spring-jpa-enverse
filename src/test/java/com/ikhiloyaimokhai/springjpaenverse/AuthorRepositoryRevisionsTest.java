package com.ikhiloyaimokhai.springjpaenverse;

import com.ikhiloyaimokhai.springjpaenverse.audit.AuditRevisionEntity;
import com.ikhiloyaimokhai.springjpaenverse.dao.AuthorRepository;
import com.ikhiloyaimokhai.springjpaenverse.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.envers.repository.support.DefaultRevisionMetadata;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorRepositoryRevisionsTest {

    @Autowired
    private AuthorRepository  repository;

    private Author author ;



    @Before
    public void save() {
        author = repository.save(
                new Author("Chinua Achebe")
        );
    }

    @Test
    public void initialRevision() {
        Revisions<Integer, Author> revisions = repository.findRevisions(author.getId());

        assertThat(revisions)
                .isNotEmpty()
                .allSatisfy(revision -> assertThat(revision.getEntity())
                        .extracting(Author::getId, Author::getName)
                        .containsExactly(author.getId(), author.getName())
                )
                .allSatisfy(revision -> {
                            DefaultRevisionMetadata metadata = (DefaultRevisionMetadata) revision.getMetadata();
                            AuditRevisionEntity revisionEntity = metadata.getDelegate();

                            assertThat(revisionEntity.getUsername()).isEqualTo("Ikhiloya");
                        }
                );
    }

    @Test
    public void updateIncreasesRevisionNumber() {
        author.setName("Elton John");

        repository.save(author);

        Optional<Revision<Integer, Author>> revision = repository.findLastChangeRevision(author.getId());

        assertThat(revision)
                .isPresent()
//                .hasValueSatisfying(rev ->
//                        assertThat(rev.getRevisionNumber()).hasValue(3)
//                )
                .hasValueSatisfying(rev ->
                        assertThat(rev.getEntity())
                                .extracting(Author::getName)
                                .isEqualTo("Elton John")
                );
    }

    @Test
    public void deletedItemWillHaveRevisionRetained() {
        repository.delete(author);

        Revisions<Integer, Author> revisions = repository.findRevisions(author.getId());

//        assertThat(revisions).hasSize(2);

        Iterator<Revision<Integer, Author>> iterator = revisions.iterator();

        Revision<Integer, Author> initialRevision = iterator.next();
        Revision<Integer, Author> finalRevision = iterator.next();

        assertThat(initialRevision)
                .satisfies(rev ->
                        assertThat(rev.getEntity())
                                .extracting(Author::getId, Author::getName)
                                .containsExactly(author.getId(), author.getName())
                );

        assertThat(finalRevision)
                .satisfies(rev -> assertThat(rev.getEntity())
                        .extracting(Author::getId, Author::getName)
                        .containsExactly(author.getId(), null, null)
                );
    }
}
