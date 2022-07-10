package subway.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LineRepositoryTest {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private StationRepository stationRepository;

    @Test
    void findById() {
        Line line = lineRepository.findByName("3호선");

        assertThat(line.getStations()).hasSize(2);
    }

    @Test
     void saveWithoutPersistStation() {
        Line line = new Line("2호선");
        line.addStations(new Station("잠실역"));

        lineRepository.save(line);

        lineRepository.flush();
    }

    @Test
    void saveWithStation() {
        Line line = new Line("2호선");
        line.addStations(stationRepository.save(new Station("잠실역", line)));

        lineRepository.save(line);

        lineRepository.flush();
    }
}
