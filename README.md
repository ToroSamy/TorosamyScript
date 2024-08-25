## Dependency
- TorosamyCore
- PlaceholderAPI
## Function
- Can customize a set of instructions and trigger them all at once with other instructions
- Customize player login and logout server execution commands
- Customize left and right clicking and walk script blocks
## Usage
1. download [TorosamyCore](https://github.com/ToroSamy/TorosamyCore) and [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) as a dependency plugin
2. put the **dependencies** and this plugin into your plugins folder and start your server
3. Modify the default configuration file generated and restart your server
## Permission
- - **Usage:** /ts reload
  - **Description:** reload this plugin
  - **Permission:** torosamyScript.reload
  <br>
- - **Usage:** /ts run group player
  - **Description:** Execute a custom instruction set for a player
  - **Permission:** torosamyScript.run
## Config

### config.yml
```yml
join-event:
  enabled: true
  #[console] [allMessage] [message] [player]
  actions: []
quit-event:
  enabled: true
  #[console] [allMessage] [message]
  actions: []
```
### lang.yml
```yml
reload-message: "&b[服务器娘]&a插件 &eTorosamyScript &a重载成功喵~"
run-group-success: "&b[服务器娘]&a成功为玩家 &e{player} &a运行了 &e{group} &a指令组"
```

### template custom-script
```yml
customName:
  world: world
  x: 10
  y: 10
  z: 10
  actions:
    - "[console] ts run <script> <player>"
    - "[op] fly"
```

## Contact Author

- qq: 1364596766
- website: https://www.torosamy.net

## License

[GPL-3.0 license](./LICENSE)
