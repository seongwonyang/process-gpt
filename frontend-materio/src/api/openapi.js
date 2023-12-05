import accountYaml from "../api/account-openapi.yaml";
import itemYaml from "../api/item-openapi.yaml";
import purchaseYaml from "../api/purchase-openapi.yaml";
import YAML from "json2yaml";

let apiSpec = "";

let accountSpec = YAML.stringify(accountYaml);
apiSpec += accountSpec;

let itemSpec = YAML.stringify(itemYaml);
apiSpec += itemSpec;

let purchaseSpec = YAML.stringify(purchaseYaml);
apiSpec += purchaseSpec;


export default apiSpec;