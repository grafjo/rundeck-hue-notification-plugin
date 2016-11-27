# Rundeck Hue Notification Plugin

[![Build Status](https://travis-ci.org/grafjo/rundeck-hue-notification-plugin.svg?branch=master)](https://travis-ci.org/grafjo/rundeck-hue-notification-plugin)

This is a [notification plugin](http://rundeck.org/docs/developer/notification-plugin.html)
for [Rundeck](http://rundeck.org/) to trigger your [Philips Hue](http://meethue.com/) lights.

## Usage

To use this module with the default configuration, just start with this:

```
./gradlew build
```

Copy  `build/libs/rundeck-hue-notification-plugin-*.jar` to your `libext` folder (e.g. /var/lib/rundeck/libext)

Restart rundeck

Go to `Installed and Bundled Plugins` and see `Notification Plugins`


## Configuration options

### bridge
The ip or hostname of your hue bridge e.g. 192.168.0.10

### user
A user account of the hue api

### light id
The id of your light you want to trigger .e.g 1


## Authors
* Johannes Graf ([@grafjo](https://github.com/grafjo))


## License

rundeck-hue-notification-plugin is released under the MIT License.
See the bundled LICENSE file for details.
