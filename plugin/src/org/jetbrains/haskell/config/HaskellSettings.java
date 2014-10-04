package org.jetbrains.haskell.config;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.haskell.util.OsUtil;
import org.jetbrains.haskell.util.UtilPackage;

import java.io.File;

@State(
        name = "HaskellConfiguration",
        storages = {
                @Storage(id = "default", file = StoragePathMacros.APP_CONFIG + "/haskell.xml")
        }
)
public class HaskellSettings implements PersistentStateComponent<HaskellSettings.State> {

    public HaskellSettings() {
        update();
    }

    @NotNull
    public static HaskellSettings getInstance() {
        HaskellSettings persisted = ServiceManager.getService(HaskellSettings.class);
        if (persisted == null) {
            return new HaskellSettings();
        }
        return persisted;
    }

    public static class State {
        public String ghcModPath;
        public String ghcModiPath;
        public String buildWrapperPath;
        public String scionBrowserPath;
        public String cabalPath;
        public String cabalDataPath;
        public String scanPath;
        public String hLintPath;
    }

    State myState = new State();

    void update() {
        if (myState.cabalPath == null) {
            myState.cabalPath = "cabal";
        }

        if (myState.cabalDataPath == null) {
            OsUtil os = UtilPackage.getOS();
            myState.cabalDataPath = os.getCabalData();
        }

        if (myState.ghcModPath == null) {
            OsUtil os = UtilPackage.getOS();
            myState.ghcModPath = os.getDefaultCabalBin() + File.separator + "ghc-mod" + os.getExe();
        }

        if (myState.ghcModiPath == null) {
            OsUtil os = UtilPackage.getOS();
            myState.ghcModiPath = os.getDefaultCabalBin() + File.separator + "ghc-modi" + os.getExe();
        }

        if (myState.buildWrapperPath == null) {
            OsUtil os = UtilPackage.getOS();
            myState.buildWrapperPath = os.getDefaultCabalBin() + File.separator + "buildwrapper" + os.getExe();
        }

        if (myState.scionBrowserPath == null) {
            OsUtil os = UtilPackage.getOS();
            myState.scionBrowserPath = os.getDefaultCabalBin() + File.separator + "scion-browser" + os.getExe();
        }

        if (myState.scanPath == null) {
            OsUtil os = UtilPackage.getOS();
            myState.scanPath = os.getDefaultCabalBin() + File.separator + "scan" + os.getExe();
        }

        if (myState.hLintPath == null) {
            OsUtil os = UtilPackage.getOS();
            myState.hLintPath = os.getDefaultCabalBin() + File.separator + "hlint" + os.getExe();
        }
    }

    @NotNull
    public State getState() {
        return myState;
    }

    public void loadState(State state) {
        myState = state;
        update();
    }

}
