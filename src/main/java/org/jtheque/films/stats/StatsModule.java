package org.jtheque.films.stats;

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
@Module(id = "jtheque-stats-module", i18n = "classpath:org/jtheque/films/stats/i18n/stats", version = "1.4.2",
        core = "2.0.2", jarFile = "jtheque-stats-module-1.4.2.jar", dependencies = "jtheque-films-module",
        updateURL = "http://jtheque.developpez.com/public/versions/StatsModule.versions")
public final class StatsModule {
    /**
     * The base name for the images.
     */
    public static final String IMAGE_BASE_NAME = "org/jtheque/films/stats/images";

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
