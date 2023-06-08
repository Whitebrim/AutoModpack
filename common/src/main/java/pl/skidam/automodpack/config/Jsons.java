package pl.skidam.automodpack.config;

import java.util.List;

public class Jsons {

    public static class ClientConfigFields {
        public String selectedModpack = "";
//        public boolean dangerScreen = true;
//        public boolean checkUpdatesButton = true;
//        public boolean deleteModpackButton = true;
//        public String username = "";
        public boolean selfUpdater = true;
        public boolean downloadDependency = true;
        public boolean autoRelauncher = false;
        public String javaExecutablePath = "";
//        public boolean openWarningWindowOnAutoRelaunch = true;
    }

    public static class ServerConfigFields {
        public String modpackName = "";
        public boolean modpackHost = true;
        public boolean generateModpackOnStart = true;
        public List<String> syncedFiles = List.of("/mods/", "/config/");
        public List<String> excludeSyncedFiles = List.of("/mods/iDontWantThisModInModpack.jar", "/config/andThisConfigToo.json", "/mods/andAllTheseMods-*.jar");
        public List<String> allowEditsInFiles = List.of("/options.txt");
//        public List<String> forceLoad = List.of("/resourcepacks/someResourcePack.zip", "/shaderpacks/someShaderPack.zip");
        public boolean optionalModpack = false;
        public boolean autoExcludeServerSideMods = true;
//        public boolean velocityMode = false; compat plugin :)
        // public boolean forceToDisableAllOtherModsOnClients = false;
        public int hostPort = 30037;
        public int hostThreads = 8;
        public String hostIp = "";
        public String hostLocalIp = "";
        public String externalModpackHostLink = "";
        public boolean reverseProxy = false;
        public boolean selfUpdater = true;
        public boolean downloadDependency = true;
        public boolean allowFabricQuiltPlayers = false;
    }

    public static class ModpackContentFields {
        public String modpackName = "";
        public String link = "";
        public String loader = "";
        public long timeStamp = -1;
        public String modpackHash = "";
        public List<ModpackContentItems> list;
        public ModpackContentFields(String link, List<ModpackContentItems> list) {
            this.link = link; // Set it on client side only
            this.list = list;
        }
        public static class ModpackContentItems {
            public String file;
            public String link; // if automodpack host (file == link) else (file != link) file is a path, link is a url
            public String size;
            public String type;
            public boolean isEditable;
            public String modId;
            public String version;
            public String sha1;
            public String murmur;

            public ModpackContentItems(String file, String link, String size, String type, boolean isEditable, String modId, String version, String sha1, String murmur) {
                this.file = file;
                this.link = link;
                this.size = size;
                this.type = type;
                this.isEditable = isEditable;
                this.modId = modId;
                this.version = version;
                this.sha1 = sha1;
                this.murmur = murmur;
            }

            @Override
            public String toString() {
                return String.format("ModpackContentItems(file=%s, link=%s, size=%s, sha512%s, murmur%s)", file, link, size, sha1, murmur);
            }
        }
    }
}
