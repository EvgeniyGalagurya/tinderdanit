import java.net.URISyntaxException;
import java.util.Objects;

public class ResourcesOps {

    // local version
//    public static String dirUnsafe(String dir) {
//        try {
//
//            String   resource = Objects.requireNonNull(ResourcesOps.class
//                    .getClassLoader()
//                    .getResource(dir))
//                    .toURI()
//                    .getPath();
//
//            if (resource.startsWith("/")) resource = resource.substring(1);
//            return resource;
//
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(String.format("Requested path `%s`not found", dir), e);
//        }
//    }
//
//
//    jar version

    public static String dirUnsafe(String dir) {
        String resource = "target/classes/";
        return resource + dir;
    }

}
