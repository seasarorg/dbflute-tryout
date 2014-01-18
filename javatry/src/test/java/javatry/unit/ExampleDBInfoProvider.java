package javatry.unit;

import java.io.File;
import java.io.IOException;

import org.seasar.dbflute.util.DfResourceUtil;

public class ExampleDBInfoProvider {

    public String getJdbcDriverClassName() {
        return "org.h2.Driver";
    }

    public String getUrl() {
        final File dir = DfResourceUtil.getBuildDir(ExampleDBInfoProvider.class);
        final String canonicalPath;
        try {
            canonicalPath = dir.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException("dir.getCanonicalPath() threw the exception: dir=" + dir, e);
        }
        final String url = "jdbc:h2:file:" + canonicalPath.replace('\\', '/') + "/exampledb/exampledb";
        return url;
    }

    public String getUser() {
        return "sa";
    }

    public String getPassword() {
        return "";
    }
}
