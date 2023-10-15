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

    @Query("SELECT ul.longUrl FROM UrlLink2 ul WHERE ul.shortUrl = :shortUrl")
    Optional<String> findLongUrlByShortUrl(@Param("shortUrl") String shortUrl);

    Optional<UrlLink> findUrlLinkByLongUrl(String longUrl);

    @Modifying
    @Query("UPDATE UrlLink2 ul SET ul.clickTimes = :clickTimes WHERE ul.id = :urlLinkId")
    void updateClickTimes(@Param("urlLinkId") Long urlLinkId, @Param("clickTimes") int clickTimes);

    Optional<UrlLink> findUrlLinkByShortUrl(String shortUrl);

    @Query("select shortUrl from UrlLink2")
    Set<String> findAllShortUrlLinks();
}
