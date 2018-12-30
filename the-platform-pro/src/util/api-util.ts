import { URL_CONST } from './url-util';

class ApiUtil {

  static postData (url, requestData) {
    return fetch(URL_CONST.API_PYTHON_HOST + url , {
        method: 'POST',
        body: requestData,
        headers: {
         // 'Content-Type': 'application/json; charset=utf-8'
        }
      }
      );
  }
  static postJavaData (url, requestData) {
    return fetch(URL_CONST.API_JAVA_HOST + url , {
        method: 'POST',
        body: requestData,
        headers: {
          // 'Content-Type': 'application/json; charset=utf-8'
        }
      }
    );
  }

  static getJavaData (url) {
    return fetch(URL_CONST.API_JAVA_HOST + url , {
        method: 'GET',
        headers: {
          // 'Content-Type': 'application/json; charset=utf-8'
        }
      }
    );
  }
  static getData (url) {
    return fetch(URL_CONST.API_PYTHON_HOST + url , {
        method: 'GET',
        headers: {
          // 'Content-Type': 'application/json; charset=utf-8'
        }
      }
    );
  }
}
export default ApiUtil;
