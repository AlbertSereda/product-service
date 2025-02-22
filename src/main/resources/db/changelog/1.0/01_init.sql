CREATE TABLE product_group
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

COMMENT ON TABLE product_group IS 'Product group information';
COMMENT ON COLUMN product_group.id IS 'Unique identifier for the product group';
COMMENT ON COLUMN product_group.name IS 'Name of the product group';

CREATE TABLE product_group_attribute
(
    id           BIGSERIAL PRIMARY KEY,
    group_id     BIGINT NOT NULL,
    attribute_id BIGINT NOT NULL,
    UNIQUE (group_id, attribute_id),
    CONSTRAINT group_id_fk FOREIGN KEY (group_id) REFERENCES product_group (id)
);

COMMENT ON TABLE product_group_attribute IS 'Groups of attributes';
COMMENT ON COLUMN product_group_attribute.id IS 'Unique identifier for the product group attribute link';
COMMENT ON COLUMN product_group_attribute.group_id IS 'Identifier of the product group';
COMMENT ON COLUMN product_group_attribute.attribute_id IS 'Identifier of the attribute';

CREATE TABLE product
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT         NOT NULL,
    name          VARCHAR(100)   NOT NULL,
    category_id   BIGINT         NOT NULL,
    price         NUMERIC(10, 2) NOT NULL,
    product_count INT            NOT NULL CHECK (product_count > 0),
    rating        NUMERIC(2, 1),
    group_id      BIGINT,
    creation_date TIMESTAMP      NOT NULL DEFAULT NOW(),
    update_date   TIMESTAMP      NOT NULL DEFAULT NOW(),
    is_archive    BOOLEAN        NOT NULL DEFAULT FALSE,
    CONSTRAINT group_id_fk FOREIGN KEY (group_id) REFERENCES product_group (id)
);

COMMENT ON TABLE product IS 'Product information';
COMMENT ON COLUMN product.id IS 'Unique identifier for the product';
COMMENT ON COLUMN product.user_id IS 'Identifier of the user who owns the product';
COMMENT ON COLUMN product.name IS 'Name of the product';
COMMENT ON COLUMN product.category_id IS 'Identifier of the product category';
COMMENT ON COLUMN product.price IS 'Price of the product';
COMMENT ON COLUMN product.product_count IS 'Quantity of the product (must be greater than 0)';
COMMENT ON COLUMN product.rating IS 'Rating of the product';
COMMENT ON COLUMN product.group_id IS 'Identifier of the product group';
COMMENT ON COLUMN product.creation_date IS 'Date of creation';
COMMENT ON COLUMN product.update_date IS 'Date of the last update';
COMMENT ON COLUMN product.is_archive IS 'Archive status of the product';

CREATE TABLE price_history
(
    id          BIGSERIAL PRIMARY KEY,
    product_id  BIGINT         NOT NULL,
    old_price   NUMERIC(10, 2) NOT NULL,
    change_date DATE           NOT NULL DEFAULT NOW(),
    CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product (id)
);

COMMENT ON TABLE price_history IS 'Price change history for products';
COMMENT ON COLUMN price_history.id IS 'Unique identifier for the price history record';
COMMENT ON COLUMN price_history.product_id IS 'Identifier of the product';
COMMENT ON COLUMN price_history.old_price IS 'Previous price of the product';
COMMENT ON COLUMN price_history.change_date IS 'Date of the price change';

CREATE TABLE attribute_info
(
    id            BIGSERIAL PRIMARY KEY,
    value         VARCHAR(100) NOT NULL,
    attribute_id  BIGINT       NOT NULL,
    product_id    BIGINT       NOT NULL,
    creation_date TIMESTAMP    NOT NULL DEFAULT NOW(),
    update_date   TIMESTAMP    NOT NULL DEFAULT NOW(),
    is_archive    BOOLEAN      NOT NULL DEFAULT FALSE,
    CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE UNIQUE INDEX attribute_info_unique_attribute_product_active
    ON attribute_info (attribute_id, product_id)
    WHERE is_archive = false;

COMMENT ON TABLE attribute_info IS 'Values for product attributes';
COMMENT ON COLUMN attribute_info.id IS 'Unique identifier for the attribute value';
COMMENT ON COLUMN attribute_info.value IS 'Value of the attribute';
COMMENT ON COLUMN attribute_info.attribute_id IS 'Identifier of the attribute';
COMMENT ON COLUMN attribute_info.product_id IS 'Identifier of the product';
COMMENT ON COLUMN attribute_info.creation_date IS 'Date of creation';
COMMENT ON COLUMN attribute_info.update_date IS 'Date of the last update';
COMMENT ON COLUMN attribute_info.is_archive IS 'Archive status of the attribute value';

CREATE TABLE product_tag
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT      NOT NULL,
    tag        VARCHAR(20) NOT NULL,
    CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product (id)
);

COMMENT ON TABLE product_tag IS 'Tags for products';
COMMENT ON COLUMN product_tag.id IS 'Unique identifier for the product tag';
COMMENT ON COLUMN product_tag.product_id IS 'Identifier of the product';
COMMENT ON COLUMN product_tag.tag IS 'Tag for the product';
