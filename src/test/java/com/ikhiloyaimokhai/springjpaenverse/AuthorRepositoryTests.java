package com.ikhiloyaimokhai.springjpaenverse;

import com.ikhiloyaimokhai.springjpaenverse.audit.AuditConfiguration;
import com.ikhiloyaimokhai.springjpaenverse.audit.AuditorAwareImpl;
import com.ikhiloyaimokhai.springjpaenverse.audit.RepositoryConfiguration;
import com.ikhiloyaimokhai.springjpaenverse.dao.AuthorRepository;
import com.ikhiloyaimokhai.springjpaenverse.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

/**
 * Integration test that use {@link DataJpaTest} to test only on JPA components.
 * Using this annotation will disable full auto-configuration and instead apply only configuration relevant to JPA tests.
 * <p>
 * By default, tests annotated with @DataJpaTest are transactional and roll back at the end of each test.
 * They also use an embedded in-memory database (replacing any explicit or usually auto-configured DataSource).
 * The @AutoConfigureTestDatabase annotation can be used to override these settings.
 */
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = ASSIGNABLE_TYPE,
        classes = {AuditorAwareImpl.class, AuditConfiguration.class, RepositoryConfiguration.class}
))
@RunWith(SpringRunner.class)
public class AuthorRepositoryTests {


    /**
     * Alternative to EntityManager for use in JPA tests.
     * Provides a subset of EntityManager methods that are useful for tests as well as
     * helper methods for common testing tasks such as persist/flush/find.
     */

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @Before
    public void save() {
        author = em.persistAndFlush(
                new Author("Chinua Achebe")
        );
    }

    @Test
    public void findByAuthorName() {
        Optional<Author> author = authorRepository.findAuthorByName("Chinua Achebe");
        assertNotNull(author);
        assertEquals("Chinua Achebe", author.get().getName());

    }


    @Test
    public void hasAuditInformation() {
        assertEquals("Ikhiloya", author.getCreatedBy());
        assertNotNull(author.getCreatedBy());
        assertNotNull(author.getCreatedDate());
        assertNotNull(author.getModifiedBy());
        assertNotNull(author.getModifiedDate());
        assertThat(author)
                .extracting(Author::getCreatedBy, Author::getCreatedDate, Author::getModifiedBy, Author::getModifiedDate,Author::getVersion)
                .isNotNull();
    }


}
