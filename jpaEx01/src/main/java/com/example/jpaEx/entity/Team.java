package com.example.jpaEx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Team {
	@Id
	private String name;
	private String emblemAnimal;
}
/*

select * from team;
DROP TABLE team;
DROP TABLE player;
insert into team values('독수리','A');
insert into team values('호랑이','B');

insert into player (pno, name, position, team_id) values (player_seq.nextval, 'James', 'CENTER', 'A');
insert into player (pno, name, position, team_id) values (player_seq.nextval, 'Ann', 'POINT_GUARD', 'A');
insert into player (pno, name, position, team_id) values (player_seq.nextval, 'Tom', 'SHOOTING_GUARD', 'A');
insert into player (pno, name, position, team_id) values (player_seq.nextval, 'Chales', 'CENTER', 'C');
commit;

*/