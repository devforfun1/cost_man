CREATE TABLE Prio_Queue_Place(
queue_id INTEGER REFERENCES Prio_Queue(id) NOT NULL,
element_id INTEGER REFERENCES Prio_Element(id) NOT NULL,
parent_element_id INTEGER REFERENCES Prio_Element(id),
child_element_id INTEGER REFERENCES Prio_Element(id)
);
