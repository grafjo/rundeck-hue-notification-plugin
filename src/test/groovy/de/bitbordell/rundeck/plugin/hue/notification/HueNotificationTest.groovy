package de.bitbordell.rundeck.plugin.hue.notification

import spock.lang.Specification

class HueNotificationTest extends Specification {

    def "Colorize"() {
        given:
        HttpRequestHandler mockedHttpRequestHandler = Mock()
        HueNotification sut = new HueNotification('192.168.0.69', 'myHueAccount', mockedHttpRequestHandler)

        when:
        sut.colorize(1, HueNotification.Color.RED)

        then:
        1 * mockedHttpRequestHandler.postJson('http://192.168.0.69/api/myHueAccount/lights/1/state', [on: true, bri: 255, sat: 255, hue: 0])
    }

    def "ColorUpdate"() {
        given:
        HueNotification sut = new HueNotification('192.168.0.69', 'myHueAccount', null)

        when:
        def colorUpdate = sut.colorUpdate(HueNotification.Color.BLUE)

        then:
        assert colorUpdate == [on: true, bri: 255, sat: 255, hue: 46920]
    }

    def "StateUrl"() {
        given:
        HueNotification sut = new HueNotification('192.168.0.69', 'myHueAccount', null)

        when:
        def stateUrl = sut.stateUrl(1)

        then:
        assert stateUrl == 'http://192.168.0.69/api/myHueAccount/lights/1/state'
    }
}
