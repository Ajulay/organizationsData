CREATE TABLE organization
(
    id       BIGSERIAL NOT NULL,
    version  INTEGER NOT NULL,
    name     CHARACTER(50) NOT NULL,
    fullname CHARACTER(150) NOT NULL,
    inn      CHARACTER(10) NOT NULL,
    kpp      CHARACTER(9) NOT NULL,
    address  CHARACTER(255) NOT NULL,
    phone    CHARACTER(16),
    active   BOOLEAN,
             PRIMARY KEY (id)
);

COMMENT ON TABLE organization IS 'Организация';
COMMENT ON COLUMN organization.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN organization.version IS 'Служебное поле hibernet';
COMMENT ON COLUMN organization.name IS 'Название';
COMMENT ON COLUMN organization.fullname IS 'Официальное (юридическое) название';
COMMENT ON COLUMN organization.inn IS 'ИНН';
COMMENT ON COLUMN organization.kpp IS 'КПП';
COMMENT ON COLUMN organization.address IS 'Адрес';
COMMENT ON COLUMN organization.phone IS 'Телефон';
COMMENT ON COLUMN organization.active IS 'Состояние организации (работает - прекратило деятельность)';

CREATE TABLE office
(
    id                BIGSERIAL NOT NULL,
    organization_id   BIGINT NOT NULL,
    name              CHARACTER(150) NOT NULL,
    address           CHARACTER(255) NOT NULL,
    phone             CHARACTER(16),
    active            BOOLEAN,
                      PRIMARY KEY (id)
);
COMMENT ON TABLE office IS 'Офис';
COMMENT ON COLUMN office.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN office.organization_id IS 'Идентификатор организации';
COMMENT ON COLUMN office.name IS 'Название';
COMMENT ON COLUMN office.address IS 'Адрес';
COMMENT ON COLUMN office.phone IS 'Телефон';
COMMENT ON COLUMN office.active IS 'Состояние офиса (работает - прекратил деятельность)';



CREATE TABLE "user"
(
    id               BIGSERIAL NOT NULL,
    office_id        BIGINT,
    first_name       CHARACTER(50) NOT NULL,
    second_name      CHARACTER(50),
    middle_name      CHARACTER(50),
    pozition         CHARACTER(50),
    phone            CHARACTER(16),
    identified       BOOLEAN,
    doc_id           BIGINT NOT NULL,
    country_id       BIGINT NOT NULL,
                     PRIMARY KEY (id)
);
COMMENT ON TABLE "user" IS 'Сотрудник';
COMMENT ON COLUMN "user".id IS 'Уникальный идентификатор';
COMMENT ON COLUMN "user".office_id IS 'Идентификатор офиса';
COMMENT ON COLUMN "user".first_name IS 'Фамилия';
COMMENT ON COLUMN "user".second_name IS 'Имя';
COMMENT ON COLUMN "user".middle_name IS 'Отчество';
COMMENT ON COLUMN "user".pozition IS 'Должность';
COMMENT ON COLUMN "user".phone IS 'Телефон';
COMMENT ON COLUMN "user".identified IS 'Сведения о подтверждении личности';
COMMENT ON COLUMN "user".doc_id IS 'Идентификатор документов, удостоверяющих личность';
COMMENT ON COLUMN "user".country_id IS 'Идентификатор гражданства';


CREATE TABLE doc
(
    id               BIGSERIAL NOT NULL,
    doc_number       CHARACTER(16),
    doc_date         TIMESTAMP WITHOUT TIME ZONE,
    doc_type_id      BIGINT NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE doc IS 'Сотрудник';
COMMENT ON COLUMN doc.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN doc.doc_number IS 'Номер документа';
COMMENT ON COLUMN doc.doc_date IS 'Дата документа';
COMMENT ON COLUMN doc.doc_type_id IS 'Идентификатор типа документа';

CREATE TABLE country
(
    id   BIGINT NOT NULL,
    name CHARACTER (35) NOT NULL,
    code CHARACTER(3) NOT NULL,
         PRIMARY KEY (id)
);

COMMENT ON TABLE country IS 'Страна';
COMMENT ON COLUMN country.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN country.name IS 'Название страны';
COMMENT ON COLUMN country.code IS 'Уникальный код страны';

CREATE TABLE doc_type
(
    id   BIGINT NOT NULL,
    name CHARACTER(55) NOT NULL,
    code CHARACTER(3) NOT NULL,
         PRIMARY KEY (id)
);

COMMENT ON TABLE doc_type IS 'Тип документа';
COMMENT ON COLUMN doc_type.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN doc_type.name IS 'Название документа';
COMMENT ON COLUMN doc_type.code IS 'Уникальный код документа';


CREATE INDEX ix_office_org ON office(organization_id, name, address, phone) ;
ALTER TABLE office
ADD CONSTRAINT office_organization_id_fk
FOREIGN KEY (organization_id) REFERENCES organization (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE INDEX ix_user_office ON "user"(office_id);
ALTER TABLE "user"
ADD CONSTRAINT user_office_id_fk
FOREIGN KEY (office_id) REFERENCES office (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE INDEX ix_user_doc ON "user"(doc_id);
ALTER TABLE "user"
ADD CONSTRAINT user_doc_id_fk
FOREIGN KEY (doc_id) REFERENCES doc(id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE INDEX ix_user_country ON "user"(country_id);
ALTER TABLE "user"
ADD CONSTRAINT user_country_id_fk
FOREIGN KEY (country_id) REFERENCES country (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE INDEX ix_doc_doc_type ON doc(doc_type_id);
ALTER TABLE doc
ADD CONSTRAINT doc_doc_type_id_fk
FOREIGN KEY (doc_type_id) REFERENCES doc_type(id) ON DELETE CASCADE ON UPDATE CASCADE;