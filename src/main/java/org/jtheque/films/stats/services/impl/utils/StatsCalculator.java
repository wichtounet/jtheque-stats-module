package org.jtheque.films.stats.services.impl.utils;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jfree.data.general.DefaultPieDataset;
import org.jtheque.films.persistence.od.able.Film;
import org.jtheque.films.services.able.IActorService;
import org.jtheque.films.services.able.IFilmsService;
import org.jtheque.films.services.able.IRealizersService;
import org.jtheque.primary.od.able.Kind;
import org.jtheque.primary.od.able.Person;
import org.jtheque.utils.count.Counter;
import org.jtheque.utils.count.Counters;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This manage enable us to generate statistics. This class is a singleton.
 *
 * @author Baptiste Wicht
 */
public final class StatsCalculator implements StatsInformations {
    private static final StatsInformations INSTANCE = new StatsCalculator();

    @Resource
    private IFilmsService filmsService;

    @Resource
    private IActorService actorService;

    @Resource
    private IRealizersService realizersService;

    private final Map<String, DefaultPieDataset> sets = new HashMap<String, DefaultPieDataset>(7);
    private final Map<String, Counters> data = new HashMap<String, Counters>(8);

    static final String GENERALS = "generals";

    private static final String FILMS_COUNTER = "films";
    private static final String ACTORS_COUNTER = "actors";
    private static final String REALIZERS_COUNTER = "realizers";

    /**
     * Private constructor, create a new <code>Statistics</code>.
     */
    private StatsCalculator() {
        super();

        data.put(GENERALS, new Counters());
        data.get(GENERALS).addCounter(FILMS_COUNTER);
        data.get(GENERALS).addCounter(ACTORS_COUNTER);
        data.get(GENERALS).addCounter(REALIZERS_COUNTER);
        data.get(GENERALS).addCounter("duration");

        sets.put(FILMS_COUNTER + "-kinds", new DefaultPieDataset());
        sets.put(FILMS_COUNTER + "-types", new DefaultPieDataset());
        sets.put(FILMS_COUNTER + "-notes", new DefaultPieDataset());
        sets.put(REALIZERS_COUNTER + "-notes", new DefaultPieDataset());
        sets.put(REALIZERS_COUNTER + "-countries", new DefaultPieDataset());
        sets.put(ACTORS_COUNTER + "-notes", new DefaultPieDataset());
        sets.put(ACTORS_COUNTER + "-countries", new DefaultPieDataset());
    }

    /**
     * Return the unique instance of <code>Statistics</code>.
     *
     * @return The instance of the class
     */
    public static StatsInformations getInstance() {
        return INSTANCE;
    }

    @Override
    public void generateStats() {
        CounterUtils.clearButGenerals(data);

        extractStatisticsOfFilms(data);
        extractStatisticsOfActors(data);
        extractStatisticsOfRealizers(data);

        populateDataset(sets.get(FILMS_COUNTER + "-kinds"), "kinds", data);
        populateDataset(sets.get(FILMS_COUNTER + "-types"), "types", data);
        populateDataset(sets.get(FILMS_COUNTER + "-notes"), "f-notes", data);
        populateDataset(sets.get(ACTORS_COUNTER + "-countries"), "a-countries", data);
        populateDataset(sets.get(ACTORS_COUNTER + "-notes"), "a-notes", data);
        populateDataset(sets.get(REALIZERS_COUNTER + "-countries"), "r-countries", data);
        populateDataset(sets.get(REALIZERS_COUNTER + "-notes"), "r-notes", data);
    }

    /**
     * Extract the statistics of the films.
     *
     * @param data The data of stats.
     */
    private void extractStatisticsOfFilms(Map<String, Counters> data) {
        data.put("kinds", new Counters());
        data.put("types", new Counters());
        data.put("f-notes", new Counters());
        data.get(GENERALS).getCounter(FILMS_COUNTER).clear();
        data.get(GENERALS).getCounter("duration").clear();

        for (Film film : filmsService.getFilms()) {
            data.get(GENERALS).getCounter("duration").add(film.getDuration());
            data.get(GENERALS).getCounter(FILMS_COUNTER).increment();

            if (film.hasKinds()) {
                for (Kind kind : film.getKinds()) {
                    data.get("kinds").getCounterOrAdd(kind.getDisplayableText()).increment();
                }
            }

            if (film.hasType()) {
                data.get("types").getCounterOrAdd(film.getTheType().getDisplayableText()).increment();
            }

            if (film.getNote() != null) {
                data.get("f-notes").getCounterOrAdd(film.getNote().getInternationalizedText()).increment();
            }
        }
    }

    /**
     * Extract the statistics of the realizers.
     *
     * @param data The data of stats.
     */
    private void extractStatisticsOfRealizers(Map<String, Counters> data) {
        extractStatisticsPersons(data, REALIZERS_COUNTER, 'r');
    }

    /**
     * Extract the statistics of the actors.
     *
     * @param data The data of stats.
     */
    private void extractStatisticsOfActors(Map<String, Counters> data) {
        extractStatisticsPersons(data, ACTORS_COUNTER, 'a');
    }

    /**
     * Extract the statistics of the persons.
     *
     * @param data The data of stats.
     * @param name The name of counter.
     * @param c    The prefix for intern counters.
     */
    private void extractStatisticsPersons(Map<String, Counters> data, String name, char c) {
        data.put(c + "-countries", new Counters());
        data.put(c + "-notes", new Counters());
        data.get(GENERALS).getCounter(name).clear();

        Collection<? extends Person> persons = ACTORS_COUNTER.equals(name) ? actorService.getActors() : realizersService.getRealizers();

        for (Person r : persons) {
            data.get(GENERALS).getCounter(name).increment();

            if (r.getTheCountry() != null) {
                data.get(c + "-countries").getCounterOrAdd(r.getTheCountry().getDisplayableText()).increment();
            }

            if (r.getNote() != null) {
                data.get(c + "-notes").getCounterOrAdd(r.getNote().getInternationalizedText()).increment();
            }
        }
    }

    /**
     * Populate a dataset with the contents of the 2 lists.
     *
     * @param dataset The dataset to populate.
     * @param name    The name of the map.
     * @param datas   The datas.
     */
    private static void populateDataset(DefaultPieDataset dataset, String name, Map<String, Counters> datas) {
        for (Map.Entry<String, Counter> counter : datas.get(name)) {
            dataset.setValue(counter.getKey(), counter.getValue().getValue());
        }
    }

    @Override
    public int getNumberOfFilms() {
        return data.get(GENERALS).getCounter(FILMS_COUNTER).getValue();
    }

    @Override
    public int getNumberOfActors() {
        return data.get(GENERALS).getCounter(ACTORS_COUNTER).getValue();
    }

    @Override
    public int getNumberOfRealizers() {
        return data.get(GENERALS).getCounter(REALIZERS_COUNTER).getValue();
    }

    @Override
    public int getTotalDurationOfFilms() {
        return data.get(GENERALS).getCounter("duration").getValue();
    }

    @Override
    public DefaultPieDataset getStatsOfFilmByKind() {
        return sets.get("films-kinds");
    }

    @Override
    public DefaultPieDataset getStatsOfFilmByType() {
        return sets.get("films-types");
    }

    @Override
    public DefaultPieDataset getStatsOfFilmByNote() {
        return sets.get("films-notes");
    }

    @Override
    public DefaultPieDataset getStatsOfActorsByCountry() {
        return sets.get("actors-countries");
    }

    @Override
    public DefaultPieDataset getStatsOfActorsByNote() {
        return sets.get("actors-notes");
    }

    @Override
    public DefaultPieDataset getStatsOfRealizersByCountry() {
        return sets.get(REALIZERS_COUNTER + "-countries");
    }

    @Override
    public DefaultPieDataset getStatsOfRealizersByNote() {
        return sets.get(REALIZERS_COUNTER + "-notes");
    }
}