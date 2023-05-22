import { amen } from '.';

export const getSubwayMarkers = (lng, lat) => amen.post('/subway', { lng, lat });

export const getSchoolMarkers = (lng, lat) => amen.post('/school', { lng, lat });

export const getHospitalMarkers = (lng, lat) => amen.post('/hospital', { lng, lat });

export const getCCTVMarkers = (lng, lat) => amen.post('/cctv', { lng, lat });

export const getBusMarkers = (lng, lat) => amen.post('/bus', { lng, lat });

export const getApartMarkers = () => amen.get('/house');

export const getSubwayDistance = aptCode => amen.get(`/subway${aptCode}`);

export const getSchoolDistance = aptCode => amen.get(`/school${aptCode}`);

export const getHospitalDistance = aptCode => amen.get(`/hospital${aptCode}`);

export const getCCTVDistance = aptCode => amen.get(`/cctv${aptCode}`);

export const getBusDistance = aptCode => amen.get(`/bus${aptCode}`);
