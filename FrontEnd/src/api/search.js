import { search } from '.';

export const getSearchSubwayByInput = input => search.get(`/subway/${input}`);

export const getSearchHouseByInput = input => search.get(`/house/${input}`);

export const getSearchGugunByInput = input => search.get(`/gugun/${input}`);
