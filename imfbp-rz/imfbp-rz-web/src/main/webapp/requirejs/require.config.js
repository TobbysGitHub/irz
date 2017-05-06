require.config({
	baseUrl: '.',
	urlArgs: "bust=" +  (new Date()).getTime(),
    paths: {
        text: "../requirejs/text",
        css: "../requirejs/css"
    },
    shim: {

    }
});
