window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    url: "/api/v1/docs",
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  });

  (function () {
      const API_KEY = 'ApiKey';
      setTimeout(function () {

        // store the api key in the local storage
        var originalAuthorize = ui.authActions.authorize;

        ui.authActions.authorize = function (payload) {
          window.localStorage.setItem(API_KEY, payload.JWTToken.value);
          return originalAuthorize(payload);
        };

        // if logout is clicked delete the api key in the local storage
        var originalLogout = ui.authActions.logout;

        ui.authActions.logout = function (payload) {
          window.localStorage.removeItem(API_KEY);
          return originalLogout(payload);
        };

        // If token already exists, load it from local storage
        const apiKey = window.localStorage.getItem(API_KEY);
        if (apiKey) {
          window.ui.preauthorizeApiKey('JWTToken', apiKey);
        }
      }, 1000);
    })();

  //</editor-fold>
};
