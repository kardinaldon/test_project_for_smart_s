package front.config;

import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

@Component
public class ValidationEventHandlerImpl implements ValidationEventHandler {

    @Override
    public boolean handleEvent(ValidationEvent event) {

        String message = event.getMessage();
        String linkedMessage = "";
        if(event.getLinkedException() != null)
            linkedMessage = event.getLinkedException().toString();

        boolean ignoreValidationEvent = true;
        if(message.contains("NumberFormatException") ||
                message.contains("is not a valid value") ||
                linkedMessage.contains("NumberFormatException") ||
                linkedMessage.contains("is not a valid value")){
            ignoreValidationEvent = false;
        }

        if(ignoreValidationEvent){
            return true;
        }else{
            String nodeName = "";
            if(event.getLocator() != null && event.getLocator().getNode() != null)
                nodeName = event.getLocator().getNode().getNodeName();

            //This is the important line
            throw new RuntimeException("Error parsing '" + nodeName + "': " + event.getMessage());

        }
    }

}
