import { AxiosApi } from "@/common/config";

//https://stackoverflow.com/questions/60610175/access-to-xmlhttprequest-at-http-localhost8080-from-origin-http-localhost

export const SensorService = {
    getSensor(id) {
        return AxiosApi.getAxiosSettings().get("/sensors/" + id + "/sensors").then(function(response){
            return response.data;
        })
        .catch(function (error) {
            if (error.response) {
                      console.log(error.response.data);
                      console.log(error.response.status);
                      console.log(error.response.headers);
                    }
                  });
            },
    getAllSensors() {
        return AxiosApi.getAxiosSettings().get("/sensors").then(function(response){
            return response.data;
        })
        .catch(function (error) {
            if (error.response) {
                      console.log(error.response.data);
                      console.log(error.response.status);
                      console.log(error.response.headers);
                    }
                  });
            }
}

export const StairService = {
    getAllStairs() {
        return AxiosApi.getAxiosSettings().get("/stairs").then(function(response){
            return response.data;
        })
        .catch(function (error) {
            if (error.response) {
              console.log(error.response.data);
              console.log(error.response.status);
              console.log(error.response.headers);
            }
          });
    }
}