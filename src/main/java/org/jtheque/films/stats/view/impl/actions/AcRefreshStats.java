package org.jtheque.films.stats.view.impl.actions;

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
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.resource.IResourceManager;
import org.jtheque.core.managers.resource.ImageType;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.films.stats.controller.able.IStatsController;
import org.jtheque.films.stats.services.able.IStatsService;
import org.jtheque.films.stats.view.able.IStatsView;
import org.jtheque.films.utils.Constants;

import java.awt.event.ActionEvent;

/**
 * Action to refresh the stats view.
 *
 * @author Baptiste Wicht
 */
public final class AcRefreshStats extends JThequeAction {
    /**
     * Construct a new AcRefreshStats.
     */
    public AcRefreshStats() {
        super("stats.actions.refresh");

        setIcon(Managers.getManager(IResourceManager.class).getIcon(Constants.IMAGE_BASE_NAME, "refresh", ImageType.PNG));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        IStatsView view = Managers.getManager(IBeansManager.class).<IStatsController>getBean("statsController").getView();

        Managers.getManager(IBeansManager.class).<IStatsService>getBean("statsService").refreshStats();
        String[] titles = Managers.getManager(IBeansManager.class).<IStatsService>getBean("statsService").getTitles();

        view.getHeaderRealizers().setDescription(titles[0]);
        view.getHeaderActors().setDescription(titles[1]);
        view.getHeaderFilms().setDescription(titles[2]);

        Managers.getManager(IViewManager.class).refresh(view.getTab());
    }
}
