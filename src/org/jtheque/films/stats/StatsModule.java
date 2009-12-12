package org.jtheque.films.stats;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.feature.Feature;
import org.jtheque.core.managers.feature.Feature.FeatureType;
import org.jtheque.core.managers.feature.IFeatureManager;
import org.jtheque.core.managers.feature.IFeatureManager.CoreFeature;
import org.jtheque.core.managers.module.annotations.Module;
import org.jtheque.core.managers.module.annotations.Plug;
import org.jtheque.core.managers.module.annotations.UnPlug;

/**
 * A JTheque Module to display stats of films, actors and realizers.
 *
 * @author Baptiste Wicht
 */
@Module(id = "jtheque-stats-module", i18n = "classpath:org/jtheque/films/stats/ressources/i18n/stats", version = "1.4.2", core = "2.0.2",
        jarFile = "jtheque-stats-module-1.4.2.jar", dependencies = "jtheque-films-module",
        updateURL = "http://jtheque.developpez.com/public/versions/StatsModule.versions")
public final class StatsModule {
    /**
     * The base name for the images.
     */
    public static final String IMAGE_BASE_NAME = "org/jtheque/films/stats/ressources/images";

    /**
     * The feature for displaying the stats.
     */
    private Feature statsFeature;

    /**
     * Plug the module.
     */
    @Plug
    public void plug() {
        IFeatureManager manager = Managers.getManager(IFeatureManager.class);

        statsFeature = manager.addSubFeature(manager.getFeature(CoreFeature.ADVANCED),
                "displayStatsAction", FeatureType.SEPARATED_ACTION, 2, StatsModule.IMAGE_BASE_NAME, "stats");
    }

    /**
     * Unplug the module.
     */
    @UnPlug
    public void unplug() {
        Managers.getManager(IFeatureManager.class).getFeature(CoreFeature.ADVANCED).removeSubFeature(statsFeature);
    }
}