import AIGenerator from "./AIGenerator";
import partialParse from "partial-json-parser";

export default class JsonAIGenerator extends AIGenerator {

    constructor(client, language){
        super(client, language);
    }

    createMessages(){
        let messages = super.createMessages();
        if(messages) {
            messages[messages.length - 1].content += ". Please generate the json in valid json format. also, Please return only the json without any natural language."
        }
        return messages;
    }
    createModel(text){
        console.log(text);
        let model;
        
        try {
            if (text.includes("null")) {
                text = text.replace(/null,/g, '"",');
                text = text.replace(/null/g, '""');
                // text = text.replace(/"[\w\s]+":\s*null,\n?/g, "");
                // text = text.replace(/"[\w\s]+":\s*null\n?/g, "");
            }

            model = partialParse(text);

            return model;

        } catch(e) {
            console.log("error to parse:" + text);

            if (this.client.onError) {
                this.client.onError(e);
            }

            // throw e;
        }
    }

}
