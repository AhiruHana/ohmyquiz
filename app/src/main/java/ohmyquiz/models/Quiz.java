package ohmyquiz.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String guid;
    private String creatorGuid;
    private Status status;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Section> sections;

    public enum Status {
        Public, Private
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCreatorGuid() {
        return creatorGuid;
    }

    public void setCreatorGuid(String creatorGuid) {
        this.creatorGuid = creatorGuid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        if (this.sections == null) {
            this.sections = new ArrayList<>();
        }
        this.sections.add(section);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "guid='" + guid + '\'' +
                ", creatorGuid='" + creatorGuid + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sections=" + sections +
                '}';
    }

    public static class Section {
        private String sectionGuid;
        private String id;
        private String title;
        private List<Question> questions;

        public String getSectionGuid() {
            return sectionGuid;
        }

        public void setSectionGuid(String sectionGuid) {
            this.sectionGuid = sectionGuid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }

        public void addQuestion(Question question) {
            if (questions == null) {
                questions = new ArrayList<>();
            }
            this.questions.add(question);
        }

        @Override
        public String toString() {
            return "Section{" +
                    "sectionGuid='" + sectionGuid + '\'' +
                    ", title='" + title + '\'' +
                    ", questions=" + questions +
                    '}';
        }
        public static class Question {
            private String questionGuid;
            private String id;
            private String content;
            private String title;
            private Difficulty difficulty;
            private List<String> correctAnswer;
            private List<Answer> answers;
    
            public enum Difficulty {
                EASY, NORMAL, HARD
            }
    
            public String getQuestionGuid() {
                return questionGuid;
            }
    
            public void setQuestionGuid(String questionGuid) {
                this.questionGuid = questionGuid;
            }
    
            public String getId() {
                return id;
            }
    
            public void setId(String id) {
                this.id = id;
            }
    
            public String getContent() {
                return content;
            }
    
            public void setContent(String content) {
                this.content = content;
            }
    
            public String getTitle() {
                return title;
            }
    
            public void setTitle(String title) {
                this.title = title;
            }
    
            public Difficulty getDifficulty() {
                return difficulty;
            }
    
            public void setDifficulty(Difficulty difficulty) {
                this.difficulty = difficulty;
            }
    
            public List<String> getCorrectAnswer() {
                return correctAnswer;
            }
    
            public void setCorrectAnswer(List<String> correctAnswer) {
                this.correctAnswer = correctAnswer;
            }
    
            public List<Answer> getAnswers() {
                return answers;
            }
    
            public void setAnswers(List<Answer> answers) {
                this.answers = answers;
            }

            public void addAnswer(Answer answer) {
                if (this.answers == null) {
                    this.answers = new ArrayList<>();
                }
                this.answers.add(answer);
            }

            @Override
            public String toString() {
                return "Question{" +
                        "questionGuid='" + questionGuid + '\'' +
                        ", content='" + content + '\'' +
                        ", title='" + title + '\'' +
                        ", difficulty=" + difficulty +
                        ", correctAnswer=" + correctAnswer +
                        ", answers=" + answers +
                        '}';
            }
    
            public static class Answer {
                private String answerGuid;
                private String content;
    
                public String getAnswerGuid() {
                    return answerGuid;
                }
    
                public void setAnswerGuid(String answerGuid) {
                    this.answerGuid = answerGuid;
                }
    
                public String getContent() {
                    return content;
                }
    
                public void setContent(String content) {
                    this.content = content;
                }
    
                @Override
                public String toString() {
                    return "Answer{" +
                            "answerGuid='" + answerGuid + '\'' +
                            ", content='" + content + '\'' +
                            '}';
                }
            }
    }

    }
}
