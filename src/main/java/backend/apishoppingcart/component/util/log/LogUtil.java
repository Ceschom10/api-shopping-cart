package backend.apishoppingcart.component.util.log;

/** Interfaz de generic logs
 * @author Cesar Amaya
 * @version 1.0
 * @since 23/05/2023
*/
public interface LogUtil { 
    public enum TYPELOG {
        INFO,ERROR,WARNING,DEBUG
    }
    boolean write(TYPELOG typeLog, String message, Object... arguments) ;
}
