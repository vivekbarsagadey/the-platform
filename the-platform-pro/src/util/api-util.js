/**
 * Created by akash on 1/11/2018.
 */


class ApiUtil {

    static getTokenVal() {
        if (this.tokenId !== undefined) {
            return this.tokenId;
        } else {
            alert("tokenId isn't updated yet");
        }
    }

    static setTokenVal(tokenId) {
        this.tokenId = tokenId;
    }


    static findBy = (url) => {
        return fetch(url, {
            method: 'get',
            headers: {
                'Content-Type': "application/json",
                "x-auth-token": ApiUtil.getTokenVal(),
            }
        })
    };


    static save = (url) => {

        return fetch(url, {
            method: 'post',
            headers: {
                'Content-Type': "application/json",
                "x-auth-token": AsyncStorage.getItem('token_id'),
            }
        })
    };

}

export default ApiUtil