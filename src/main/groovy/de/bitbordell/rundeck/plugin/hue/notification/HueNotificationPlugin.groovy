package de.bitbordell.rundeck.plugin.hue.notification

import com.dtolabs.rundeck.core.plugins.Plugin
import com.dtolabs.rundeck.core.plugins.configuration.PropertyScope
import com.dtolabs.rundeck.plugins.ServiceNameConstants
import com.dtolabs.rundeck.plugins.descriptions.PluginDescription
import com.dtolabs.rundeck.plugins.descriptions.PluginProperty
import com.dtolabs.rundeck.plugins.notification.NotificationPlugin


@Plugin(name = "hue-notification", service = ServiceNameConstants.Notification)
@PluginDescription(title = "Hue notification", description = "Sends the job status to a philips hue light blub.")
class HueNotificationPlugin implements NotificationPlugin {

    @PluginProperty(name = "bridge", title = "Bridge", description = "The ip / hostname of your hue bridge", required = true, scope = PropertyScope.Project)
    private String bridge;

    @PluginProperty(name = "user", title = "User", description = "The api user", required = true, scope = PropertyScope.Project)
    private String user;

    @PluginProperty(name = "light", title = "Light", description = "The id your light blub", required = true)
    private Integer light;

    @Override
    boolean postNotification(String trigger, Map executionData, Map config) {

        println("hue-notification plugin triggered by ${trigger} ...")

        def hueNotification = createHueNotification()

        if ("start" == trigger) {
            hueNotification.colorize(light, HueNotification.Color.BLUE)

        } else if ("success" == trigger) {
            hueNotification.colorize(light, HueNotification.Color.GREEN)

        } else if ("failure" == trigger) {
            hueNotification.colorize(light, HueNotification.Color.RED)

        } else {
            println("hue notification plugin: error")
            return false
        }
        println("hue notification plugin: ok")
        return true
    }

    private HueNotification createHueNotification() {
        new HueNotification(bridge, user, new HttpRequestHandler())
    }

}
