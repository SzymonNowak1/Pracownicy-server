CREATE TABLE public.workers
(
    id bigserial NOT NULL,
    userid bigint,
    firstname character varying,
    lastname character varying,
    birthday date,
    address character varying,
    phonenumber character varying,
    PRIMARY KEY (id)
);

ALTER TABLE public.workers
    OWNER to postgres;

ALTER TABLE public.workers
    ADD CONSTRAINT "FK_workers_userid" FOREIGN KEY (userid)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX "fki_FK_workers_userid"
    ON public.workers(userid);



CREATE TABLE public.positions
(
    id bigserial NOT NULL,
    name character varying NOT NULL,
    description character varying,
    base numeric(10, 2),
    PRIMARY KEY (id)
);

ALTER TABLE public.positions
    OWNER to postgres;



CREATE TABLE public.workerpositions
(
    workerid bigint NOT NULL,
    positionid bigint NOT NULL
);

ALTER TABLE public.workerpositions
    OWNER to postgres;

ALTER TABLE public.workerpositions
    ADD CONSTRAINT "FK_workerposition_worker" FOREIGN KEY (workerid)
    REFERENCES public.workers (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX "fki_FK_workerposition_worker"
    ON public.workerpositions(workerid);

ALTER TABLE public.workerpositions
    ADD CONSTRAINT "FK_workerposition_position" FOREIGN KEY (positionid)
    REFERENCES public.positions (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX "fki_FK_workerposition_position"
    ON public.workerpositions(positionid);


CREATE TABLE public.salarytargets
(
    id bigserial NOT NULL,
    workerid bigint NOT NULL,
    name character varying NOT NULL,
    bankaccount character varying NOT NULL,
    selected boolean NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.salarytargets
    OWNER to postgres;

ALTER TABLE public.salarytargets
    ADD CONSTRAINT "FK_salarytargets_workerid" FOREIGN KEY (workerid)
    REFERENCES public.workers (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX "fki_FK_salarytargets_workerid"
    ON public.salarytargets(workerid);

CREATE TABLE public.taxes
(
    id bigserial NOT NULL,
    total numeric(10, 2) NOT NULL,
    gross numeric(10, 2) NOT NULL,
    net numeric(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.taxes
    OWNER to postgres;

CREATE TABLE public.salaries
(
    id bigserial NOT NULL,
    salarytargetid bigint NOT NULL,
    taxid bigint NOT NULL,
    date date NOT NULL,
    bonus numeric(10, 2),
    status character varying,
    PRIMARY KEY (id)
);

ALTER TABLE public.salaries
    OWNER to postgres;

ALTER TABLE public.salaries
    ADD CONSTRAINT "FK_salaries_salarytargetid" FOREIGN KEY (salarytargetid)
    REFERENCES public.salarytargets (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX "fki_FK_salaries_salarytargetid"
    ON public.salaries(salarytargetid);

ALTER TABLE public.salaries
    ADD CONSTRAINT "FK_salaries_taxid" FOREIGN KEY (taxid)
    REFERENCES public.taxes (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX "fki_FK_salaries_taxid"
    ON public.salaries(taxid);

CREATE TABLE public.configuration
(
    id bigserial NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    value character varying NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.configuration
    OWNER to postgres;

