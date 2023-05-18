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
  const isShowCCTV = ref(false);
  const isShowSchool = ref(false);
  const isShowHospital = ref(false);
  const isShowSubway = ref(false);
  const isShowBus = ref(false);

  const apartMarkers = ref([]);
  const schoolMarkers = ref([]);
  const cctvMarkers = ref([]);
  const hospitalMarkers = ref([]);
  const subwayMarkers = ref([]);
  const busMarkers = ref([]);

  const sidoGugun = ref([]);

  const _schoolMarkers = computed(() => {
    return isShowSchool.value ? [...schoolMarkers.value] : [];
  });
  const _cctvMarkers = computed(() => {
    return isShowCCTV.value ? [...cctvMarkers.value] : [];
  });
  const _hospitalMarkers = computed(() => {
    return isShowHospital.value ? [...hospitalMarkers.value] : [];
  });
  const _subwayMarkers = computed(() => {
    return isShowSubway.value ? [...subwayMarkers.value] : [];
  });
  const _busMarkers = computed(() => {
    return isShowBus.value ? [...busMarkers.value] : [];
  });

  const setApartMarkers = async (lng, lat) => {
    try {
      const response = await getApartMarkers(lng, lat);
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
    _schoolMarkers,
    _cctvMarkers,
    _hospitalMarkers,
    _subwayMarkers,
    _busMarkers,
  };
});
