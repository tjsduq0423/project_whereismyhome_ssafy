import { member } from '.';

export const logout = () => member.post('/logout');

export const login = (id, password) => member.post('/login', { id, password });

export const join = (id, password, name, email) =>
  member.post('/join', { id, password, name, email });
