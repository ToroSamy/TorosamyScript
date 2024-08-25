package net.torosamy.torosamyScript.config;

import net.torosamy.torosamyCore.config.TorosamyConfig;


import java.util.List;

public class MainConfig extends TorosamyConfig {
    public JoinEvent joinEvent = new JoinEvent();
    public class JoinEvent extends TorosamyConfig {
        public Boolean enabled;
        public List<String> actions;
    }


    public QuitEvent quitEvent = new QuitEvent();
    public class QuitEvent extends TorosamyConfig {
        public Boolean enabled;
        public List<String> actions;
    }


}
