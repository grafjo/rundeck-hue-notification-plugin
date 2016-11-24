package de.bitbordell.rundeck.plugin.hue.notification

class HueNotification {

    private String bridge
    private String account
    private HttpRequestHandler httpRequestHandler

    HueNotification(String bridge, String account, HttpRequestHandler httpRequestHandler) {
        this.bridge = bridge
        this.account = account
        this.httpRequestHandler = httpRequestHandler
    }

    def colorize(Integer lightId, Color color) {

        def url = stateUrl(lightId)
        def colorUpdate = colorUpdate(color)

        httpRequestHandler.postJson(url, colorUpdate)
    }

    def colorUpdate(Color color) {
        [on: true, bri: 255, sat: 255, hue: color.getColorCode()]
    }

    def stateUrl(Integer lightId) {
        "http://${bridge}/api/${account}/lights/${lightId}/state"
    }

    static enum Color {

        RED(0), GREEN(25717), BLUE(46920)

        private final int colorCode

        Color(int colorCode) {
            this.colorCode = colorCode
        }

        int getColorCode() {
            colorCode
        }
    }
}
