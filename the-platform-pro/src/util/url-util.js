/**
 * Created by akash on 1/11/2018.
 */

//const SERVER_API_URL = "http://192.168.0.119";
const SERVER_API_URL = "http://192.168.0.123";
const SERVER_API_PORT = "8080";
const BASE_API_URL = SERVER_API_URL + ":"+SERVER_API_PORT +"/";

const UrlUtil =
    {
        AUTH_URL  : BASE_API_URL+"login/",
        ITEM_LOOKUP_URL:BASE_API_URL+"physicalInventory/",
        ITEM_VALLIDATE_BARCODE_LOOKUP_URL:BASE_API_URL+"physicalInventory/"+"validateBarcode?"

    };

export default UrlUtil