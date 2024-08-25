## Dependency
- TorosamyCore
- PlaceholderAPI
## Usage
1. download [TorosamyCore](https://github.com/ToroSamy/TorosamyCore) and [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) as a dependency plugin
2. put the **dependencies** and this plugin into your plugins folder and start your server
3. Modify the default configuration file generated and restart your server
## Permission
- - **Usage:** /tj reload
  - **Description:** reload this plugin
  - **Permission:** torosamyScript.reload
  <br>
- - **Usage:** /tj title main-text sub-text
  - **Description:** Send a title info to all players
  - **Permission:** torosamyScript.title.all
  <br>
- - **Usage:** /tj title main-text sub-text player-name
  - **Description:** Send a title to a single player
  - **Permission:** torosamyScript.title.single
## Config

### config.yml
```yml
Script-event:
  enabled: true
  #[console] [allMessage] [message] [player]
  actions: []

quit-event:
  enabled: true
  #[console] [allMessage] [message]
  actions: []


first-Script:
  enabled: true
  #[console] [allMessage] [message] [player]
  actions: []
  welcome-reward:
    enabled: true
    #How long does it take for other players to welcome new players to the server in order to receive rewards
    time: 30
    #[console] [allMessage] [message] [player]
    actions: []
    #Welcome keywords, including these can earn rewards
    keys:
      - "welcome"
```
### lang.yml
```yml
reload-message: "&b[服务器娘]&a插件 &eTorosamyScript &a重载成功喵~"
send-successfully: "&b[服务器娘]&a发送成功!"
```

## Contact Author

- qq: 1364596766
- website: https://www.torosamy.net

## License

[MIT](./LICENSE)
