// kakaoMapsPlugin.js
const KakaoMapsPlugin = {
  install(app) {
    const script = document.createElement('script');
    script.src = `https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_APP_KAKAOMAP_KEY
    }&libraries=services,clusterer`;
    script.async = true;
    script.onload = () => {
      app.config.globalProperties.$kakao = window.kakao;
    };
    document.head.appendChild(script);
  },
};

export default KakaoMapsPlugin;
