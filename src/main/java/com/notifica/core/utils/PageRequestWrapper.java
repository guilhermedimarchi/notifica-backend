package com.notifica.core.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

public class PageRequestWrapper {
    private Integer pageNumber;
    private Integer pageSize;
    private String[] orderBy;

    private SearchTerm[] searchTerms;

    public class SearchTerm {

        private String term;
        private String type;

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String[] getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
    }

    public Pageable getPageable() {
        Pageable pageable;
        if (orderBy == null) {
            pageable = new PageRequest(pageNumber, pageSize);
        } else {
            pageable = new PageRequest(pageNumber, pageSize, new Sort(orderBy));
        }
        return pageable;
    }

    public <T> Specification<T> getSpecification() {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();
            if (searchTerms != null) {

                for (SearchTerm searchTerm : searchTerms) {
                    String term = searchTerm.getTerm();
                    if (term != null && !term.isEmpty()) {
                        String[] data = term.split(" ");
                        if (data.length >= 3) {
                            String prop = data[0];
                            String operator = data[1];
                            StringBuilder sBuilder = new StringBuilder();
                            for (int i = 2; i < data.length; i++) {
                                sBuilder.append(data[i]).append(" ");
                            }
                            String value = sBuilder.toString().trim();
                            Predicate predicateTerm = null;

                            Expression<String> col;

                            if (prop.contains(".")) {
                                String[] split = prop.split("\\.");

                                Join<?, ?> lastJoin = null;

                                for (int i = 0; i < split.length - 1; i++) {
                                    if (lastJoin == null) {
                                        lastJoin = root.join(split[i]);
                                    } else {
                                        lastJoin = lastJoin.join(split[i]);
                                    }

                                }

                                col = lastJoin.get(split[split.length - 1])
                                        .as(String.class);
                            } else {
                                col = root.get(prop).as(String.class);
                            }

                            switch (operator) {
                                case "%":
                                    predicateTerm = builder.like(builder.lower(col), value.toLowerCase());
                                    break;
                                case ">":
                                    predicateTerm = builder.greaterThan(builder.lower(col), value.toLowerCase());
                                    break;
                                case ">=":
                                    predicateTerm = builder
                                            .greaterThanOrEqualTo(builder.lower(col), value.toLowerCase());
                                    break;
                                case "=":
                                    predicateTerm = builder.equal(builder.lower(col), value.toLowerCase());
                                    break;
                                case "=<":
                                    predicateTerm = builder.lessThanOrEqualTo(builder.lower(col), value.toLowerCase());
                                    break;
                                case "<":
                                    predicateTerm = builder
                                            .lessThan(builder.lower(col), value.toLowerCase());
                                    break;
                                default:
                                    continue;
                            }

                            predicate.getExpressions().add(
                                    builder.and(predicateTerm));
                        }

                    }
                }

            }
            return predicate;
        };
    }

    public SearchTerm[] getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(SearchTerm[] searchTerms) {
        this.searchTerms = searchTerms;
    }

}
