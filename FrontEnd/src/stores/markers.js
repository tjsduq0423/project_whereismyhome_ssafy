import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import {
  getApartMarkers,
  getSubwayMarkers,
  getSchoolMarkers,
  getHospitalMarkers,
  getBusMarkers,
} from '@/api/amen';
import { getAvgInGugun } from '@/api/info';

export const useMarkersStore = defineStore('markers', () => {
  // 마커 데이터 처리
  const apartMarkers = ref([]);
  const schoolMarkers = ref([]);
  const hospitalMarkers = ref([]);
  const subwayMarkers = ref([]);
  const busMarkers = ref([]);
  const sidoGugun = ref([]);
  // 지역구별 평균 매매가 커스텀 오버레이 구별 평균 매매가
  const avgInGugun = ref([]);

  // 편의시설 마커 visible 관리 변수
  const isShowSchool = ref(false);
  const isShowHospital = ref(false);
  const isShowSubway = ref(false);
  const isShowBus = ref(false);

  // 마커로딩 딜레이 시간 계산값.
  const delayTimeByMarker = computed(() => {
    let defalutDelay = 500;
    if (isShowBus.value) defalutDelay += 250;
    if (isShowHospital.value) defalutDelay += 250;
    return defalutDelay;
  });

  // 아파트 마커 visible 필터링.
  const priceRange = ref('10');
  const buildYearRange = ref(100);

  const setAvgInGugun = async () => {
    try {
      const response = await getAvgInGugun();
      avgInGugun.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };

  const setApartMarkers = async (lng, lat, zoomlevel) => {
    try {
      const response = await getApartMarkers(lng, lat, zoomlevel > 3 ? 3 : zoomlevel);
      apartMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setSchoolMarkers = async (lng, lat, zoomlevel) => {
    try {
      const response = await getSchoolMarkers(lng, lat, zoomlevel > 3 ? 3 : zoomlevel);
      schoolMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setHospitalMarkers = async (lng, lat, zoomlevel) => {
    try {
      const response = await getHospitalMarkers(lng, lat, zoomlevel > 3 ? 3 : zoomlevel);
      hospitalMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setSubwayMarkers = async (lng, lat, zoomlevel) => {
    try {
      const response = await getSubwayMarkers(lng, lat, zoomlevel > 3 ? 3 : zoomlevel);
      subwayMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setBusMarkers = async (lng, lat, zoomlevel) => {
    try {
      const response = await getBusMarkers(lng, lat, zoomlevel > 3 ? 3 : zoomlevel);
      busMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };

  return {
    avgInGugun,
    setAvgInGugun,
    buildYearRange,
    priceRange,
    apartMarkers,
    schoolMarkers,
    hospitalMarkers,
    subwayMarkers,
    busMarkers,
    setApartMarkers,
    setSubwayMarkers,
    setSchoolMarkers,
    setHospitalMarkers,
    setBusMarkers,
    sidoGugun,
    isShowSchool,
    isShowHospital,
    isShowSubway,
    isShowBus,
    delayTimeByMarker,
  };
});
