import {Authority} from './authority.model';

export class User {
  id: number;
  username: string;
  password: string;
  mailaziendale: string;
  authorities: Authority[];
}
