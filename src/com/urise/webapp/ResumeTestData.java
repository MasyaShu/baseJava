package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = CreateResume();
        printResume(resume);
    }

    static Resume CreateResume() {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        for (ContactType c : ContactType.values()) {
            switch (c.name()) {
                case "TEL":
                    contacts.put(c, "+7(921) 855-0482");
                    break;
                case "SKYPE":
                    contacts.put(c, "grigory.kislin");
                    break;
                case "EMAIL":
                    contacts.put(c, "gkislin@yandex.ru");
                    break;
                case "LINKEDLN":
                    contacts.put(c, "https://www.linkedin.com/in/gkislin");
                    break;
                case "GITHUB":
                    contacts.put(c, "https://github.com/gkislin");
                    break;
                case "STACKOVERFLOW":
                    contacts.put(c, "https://stackoverflow.com/users/548473");
                    break;
                case "HOMEPAGE":
                    contacts.put(c, "http://gkislin.ru/");
            }
        }
        Map<SectionType, AbstractSection> sections = new HashMap<>();
        for (SectionType st : SectionType.values()) {
            if (st.name().equals("PERSONAL") || st.name().equals("OBJECTIVE")) {
                switch (st.name()) {
                    case "OBJECTIVE":
                        sections.put(st, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
                        break;
                    case "PERSONAL":
                        sections.put(st, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
                }
            } else if (st.name().equals("ACHIEVEMENT") || st.name().equals("QUALIFICATIONS")) {
                switch (st.name()) {
                    case "ACHIEVEMENT":
                        List<String> achievement = new ArrayList<>();
                        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. ");
                        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ");
                        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. ");
                        sections.put(st, new StringListSection(achievement));
                        break;
                    case "QUALIFICATIONS":
                        List<String> qualifications = new ArrayList<>();
                        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
                        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce ");
                        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
                        sections.put(st, new StringListSection(qualifications));
                }
            } else if (st.name().equals("EXPERIENCE") || st.name().equals("EDUCATION")) {
                switch (st.name()) {
                    case "EXPERIENCE":
                        List<Experience> experience = new ArrayList<>();
                        List<Experience.Position> position = new ArrayList<>();
                        position.add(new Experience.Position(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
                        experience.add(new Experience(new Link("http://javaops.ru/", "Java Online Projects"), position));
                        sections.put(st, new OrganizationSection(experience));
                        break;
                    case "EDUCATION":
                        List<Experience> education = new ArrayList<>();
                        List<Experience.Position> position1 = new ArrayList<>();
                        position1.add(new Experience.Position(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Аспирантура (программист С, С++)", ""));
                        position1.add(new Experience.Position(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Инженер (программист Fortran, C)", ""));
                        education.add(new Experience(new Link("http://www.ifmo.ru/", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики"), position1));
                        sections.put(st, new OrganizationSection(education));
                }
            }
        }
        Resume resume = new Resume("Григорий Кислин", contacts, sections);
        return resume;
    }

    static void printResume(Resume resume) {
        System.out.println(resume.getFullName());
        for (ContactType c : ContactType.values()) {
            if (resume.getContact().get(c) != null) {
                System.out.println(c.getTitle() + ": " + resume.getContact().get(c));
            }
        }

        for (SectionType st : SectionType.values()) {
            System.out.println(st.getTitle());
            AbstractSection section = resume.getSections().get(st);
            if (st.name().equals("PERSONAL") || st.name().equals("OBJECTIVE")) {
                System.out.println(section.getInfo());
            } else if (st.name().equals("ACHIEVEMENT") || st.name().equals("QUALIFICATIONS")) {
                List<String> listSection = (List<String>) section.getInfo();
                for (String sls : listSection) {
                    System.out.println(sls);
                }
            } else if (st.name().equals("EXPERIENCE") || st.name().equals("EDUCATION")) {
                List<Experience> listSection = (List<Experience>) section.getInfo();
                for (Experience sls : listSection) {
                    System.out.println(sls.getUrl());
                    for (Experience.Position poz : sls.getPositions()) {
                        System.out.println(poz.getData() + " - " + poz.getDataTo() + ": " + poz.getEstablishment());
                        System.out.println(poz.getPosition());
                    }

                }
            }
        }
    }
}
