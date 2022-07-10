package subway.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    void save() {
        Member member = new Member("mat");
        member.addFavorites(favoriteRepository.save(new Favorite()));
        memberRepository.save(member);

        memberRepository.flush();
    }
}
