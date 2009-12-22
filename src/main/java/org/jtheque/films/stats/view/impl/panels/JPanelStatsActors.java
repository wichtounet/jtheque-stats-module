package org.jtheque.films.stats.view.impl.panels;

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

import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.utils.ui.GridBagUtils;

/**
 * A stats panel for actors.
 *
 * @author Baptiste Wicht
 */
public final class JPanelStatsActors extends PanelStats {
    private static final long serialVersionUID = 5244577639814727219L;

    private static final double AN_HALF = 0.5;

    /**
     * Construct a new <code>JPanelStatsActors</code>.
     */
    public JPanelStatsActors() {
        super();

        PanelBuilder builder = new PanelBuilder(this);

        setHeader("stats.actors.title", getStatsService().getTitles()[1]);

        builder.add(getHeader(), builder.gbcSet(0, 0));

        builder.add(createPieChartPanel("stats.actors.pie.country", getStatsService().getInformations().getStatsOfActorsByCountry()),
                builder.gbcSet(0, 1, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, AN_HALF));

        builder.add(createPieChartPanel("stats.actors.pie.note", getStatsService().getInformations().getStatsOfActorsByNote()),
                builder.gbcSet(0, 2, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, AN_HALF));
    }

    @Override
    public void refreshText() {
        getHeader().setTitle(getResources().getMessage("stats.actors.title"));
        getHeader().setDescription(getStatsService().getInformations().getNumberOfActors() + " " +
                getResources().getMessage("stats.actors.actors").toLowerCase(getResources().getCurrentLocale()));
    }
}