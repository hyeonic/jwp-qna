package subway.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StationRepositoryTest {

    @Autowired
    protected StationRepository stationRepository;

    @Test
    void save() {
        Station station = stationRepository.save(new Station("잠실역"));

        System.out.println(station.getId());

        assertAll(() -> {
            assertThat(station.getId()).isNotNull();
            assertThat(station.getName()).isEqualTo("잠실역");
        });
    }

    @Test
    void findById() {
        Station station = stationRepository.save(new Station("잠실역"));

        assertThat(station.getId()).isNotNull();
    }

    @Test
    void findByName() {
        stationRepository.save(new Station("잠실역"));
        String actual = stationRepository.findByName("잠실역").getName();

        assertThat(actual).isEqualTo("잠실역");
    }

    @Test
    void identity() {
        Station station1 = stationRepository.save(new Station("잠실역"));
        Station station2 = stationRepository.findById(station1.getId()).get();

        assertThat(station1 == station2).isTrue();
    }

    @Test
    void update() {
        Station station = stationRepository.save(new Station("잠실역"));
        station.changeName("몽촌토성역");

        Station foundStation = stationRepository.findByName("몽촌토성역");

        assertThat(foundStation).isNotNull();
    }
}
