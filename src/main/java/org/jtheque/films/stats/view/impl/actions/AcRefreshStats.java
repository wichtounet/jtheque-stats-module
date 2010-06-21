package org.jtheque.films.stats.view.impl.actions;

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