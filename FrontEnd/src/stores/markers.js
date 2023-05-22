import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import {
  getApartMarkers,
  getSubwayMarkers,
  getSchoolMarkers,
  getHospitalMarkers,
  getCCTVMarkers,
  getBusMarkers,
} from '@/api/amen';

export const useMarkersStore = defineStore('markers', () => {
  // 마커 데이터 처리
  const apartMarkers = ref([]);
  const schoolMarkers = ref([]);
  const cctvMarkers = ref([]);
  const hospitalMarkers = ref([]);
  const subwayMarkers = ref([]);
  const busMarkers = ref([]);
  const sidoGugun = ref([]);

  // 편의시설 마커 visible 관리 변수
  const isShowCCTV = ref(false);
  const isShowSchool = ref(false);
  const isShowHospital = ref(false);
  const isShowSubway = ref(false);
  const isShowBus = ref(false);

  // 마커로딩 딜레이 시간 계산값.
  const delayTimeByMarker = computed(() => {
    let defalutDelay = 500;
    if (isShowBus.value) defalutDelay += 250;
    if (isShowHospital.value) defalutDelay += 250;
    if (isShowCCTV.value) defalutDelay += 500;
    return defalutDelay;
  });

  // 아파트 마커 visible 필터링.
  const priceRange = ref('10');
  const buildYearRange = ref(100);
  const filteredApartMarkers = computed(() => {
    return apartMarkers.value.filter(marker => {
      return (
        (parseInt(priceRange.value) === 21 || marker.avg / 10000 < parseInt(priceRange.value)) &&
        2023 - marker.buildYear <= buildYearRange.value
      );
    });
  });
  const setApartMarkers = async () => {
    try {
      const response = await getApartMarkers();
      apartMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setSchoolMarkers = async (lng, lat) => {
    try {
      const response = await getSchoolMarkers(lng, lat);
      schoolMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setCCTVMarkers = async (lng, lat) => {
    try {
      const response = await getCCTVMarkers(lng, lat);
      cctvMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setHospitalMarkers = async (lng, lat) => {
    try {
      const response = await getHospitalMarkers(lng, lat);
      hospitalMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setSubwayMarkers = async (lng, lat) => {
    try {
      const response = await getSubwayMarkers(lng, lat);
      subwayMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setBusMarkers = async (lng, lat) => {
    try {
      const response = await getBusMarkers(lng, lat);
      busMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };

  return {
    filteredApartMarkers,
    buildYearRange,
    priceRange,
    apartMarkers,
    schoolMarkers,
    cctvMarkers,
    hospitalMarkers,
    subwayMarkers,
    busMarkers,
    setApartMarkers,
    setSubwayMarkers,
    setSchoolMarkers,
    setCCTVMarkers,
    setHospitalMarkers,
    setBusMarkers,
    sidoGugun,
    isShowCCTV,
    isShowSchool,
    isShowHospital,
    isShowSubway,
    isShowBus,
    delayTimeByMarker,
  };
});
