package goit.com.shorturlproject.v1.url.repository;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UrlRepository extends JpaRepository<UrlLink, Long> {
    Set<UrlLink> findAllByUserId(Long userId);

    @Query("SELECT ul.longUrl FROM UrlLink ul WHERE ul.shortUrl = :shortUrl")
    Optional<String> findLongUrlByShortUrl(@Param("shortUrl") String shortUrl);

    Optional<UrlLink> findUrlLinkByLongUrl(String longUrl);

    @Modifying
    @Query("UPDATE UrlLink ul SET ul.clickTimes = :clickTimes WHERE ul.id = :urlLinkId")
    void updateClickTimes(@Param("urlLinkId") Long urlLinkId, @Param("clickTimes") int clickTimes);

    Optional<UrlLink> findUrlLinkByShortUrl(String shortUrl);

    @Query("select shortUrl from UrlLink")
    Set<String> findAllShortUrlLinks();

    //Запрос на удаление ссылки по его id
    //Request to delete a link by its id
    @Modifying
    @Query("DELETE FROM UrlLink ul WHERE ul.user.id = :userId and ul.id = :urlLinkId")
    void deleteUrlLinkById(@Param("userId") Long userId, @Param("urlLinkId") Long urlLinkId);
}
