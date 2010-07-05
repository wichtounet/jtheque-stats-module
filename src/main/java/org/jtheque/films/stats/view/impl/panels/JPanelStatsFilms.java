package org.jtheque.films.stats.view.impl.panels;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.utils.ui.GridBagUtils;

/**
 * A stats panel for films.
 *
 * @author Baptiste Wicht
 */
public final class JPanelStatsFilms extends PanelStats {
    private static final long serialVersionUID = 3556875889469725943L;

    private static final double A_THIRD = 0.33;

    /**
     * Construct a new JPanelStatsFilms.
     */
    public JPanelStatsFilms() {
        super();

        PanelBuilder builder = new PanelBuilder(this);

        setHeader("stats.films.title", getStatsService().getTitles()[2]);

        builder.add(getHeader(), builder.gbcSet(0, 0));

        builder.add(createPieChartPanel("stats.films.pie.type", getStatsService().getInformations().getStatsOfFilmByType()),
                builder.gbcSet(0, 1, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, A_THIRD));

        builder.add(createPieChartPanel("stats.films.pie.kind", getStatsService().getInformations().getStatsOfFilmByKind()),
                builder.gbcSet(0, 2, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, A_THIRD));

        builder.add(createPieChartPanel("stats.films.pie.note", getStatsService().getInformations().getStatsOfFilmByNote()),
                builder.gbcSet(0, 3, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, A_THIRD));
    }

    @Override
    public void refreshText() {
        getHeader().setTitle(getResources().getMessage("stats.films.title"));
        getHeader().setDescription(getStatsService().getInformations().getNumberOfFilms() + " " +
                getResources().getMessage("stats.films.films").toLowerCase(getResources().getCurrentLocale()));
    }
}
