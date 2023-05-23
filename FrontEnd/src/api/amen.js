import { amen } from '.';

export const getSubwayMarkers = (lng, lat, zoomlevel) =>
  amen.post('/subway', { lng, lat, zoomlevel });

export const getSchoolMarkers = (lng, lat, zoomlevel) =>
  amen.post('/school', { lng, lat, zoomlevel });

export const getHospitalMarkers = (lng, lat, zoomlevel) =>
  amen.post('/hospital', { lng, lat, zoomlevel });

export const getBusMarkers = (lng, lat, zoomlevel) => amen.post('/bus', { lng, lat, zoomlevel });

export const getApartMarkers = (lng, lat, zoomlevel) =>
  amen.post('/house', { lng, lat, zoomlevel });

export const getSubwayDistance = aptCode => amen.get(`/subway/${aptCode}`);

export const getSchoolDistance = aptCode => amen.get(`/school/${aptCode}`);

export const getHospitalDistance = aptCode => amen.get(`/hospital/${aptCode}`);

export const getBusDistance = aptCode => amen.get(`/bus/${aptCode}`);
