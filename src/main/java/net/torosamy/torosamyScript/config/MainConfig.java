package net.torosamy.torosamyScript.config;

import net.torosamy.torosamyCore.config.IConfigManage;


import java.util.List;

public class MainConfig implements IConfigManage {
    public JoinEvent joinEvent = new JoinEvent();
    public class JoinEvent implements IConfigManage {
        public Boolean enabled;
        public List<String> actions;
    }


    public QuitEvent quitEvent = new QuitEvent();
    public class QuitEvent implements IConfigManage {
        public Boolean enabled;
        public List<String> actions;
    }


}
