import { member } from '.';

export const logout = () => member.post('/logout');

export const login = (id, password) => member.post('/login', { id, password });

export const join = (id, password, name, email) =>
  member.post('/join', { id, password, name, email });

export const sendEmail = email => member.get(`/${email}`);

export const changePassword = (id, password) => member.put('/change', { id, password });

export const checkMember = id => member.get(`/check/${id}`);
