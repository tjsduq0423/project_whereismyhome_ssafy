// kakaoMapsPlugin.js
import { useKakaoStore } from '@/stores/kakao';
import { storeToRefs } from 'pinia';

const KakaoMapsPlugin = {
  install() {
    const script = document.createElement('script');
    const kakaoStore = useKakaoStore();
    const { kakao } = storeToRefs(kakaoStore);
    script.src = `https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_APP_KAKAOMAP_KEY
    }&libraries=services,clusterer`;
    script.onload = () => {
      kakao.value = window.kakao;
    };
    document.head.appendChild(script);
  },
};

export default KakaoMapsPlugin;
