/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package playground.mongo;

import playground.Person;
import rx.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Sebastien Deleuze
 */
@Controller
public class MongoPersonController {

	private final MongoPersonRepository repository;

	@Autowired
	public MongoPersonController(MongoPersonRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(path = "/mongo", method = RequestMethod.POST)
	public Observable<Void> create(@RequestBody Observable<Person> personStream) {
		return this.repository.save(personStream);
	}

	@RequestMapping(path = "/mongo", method = RequestMethod.GET)
	@ResponseBody
	public Observable<Person> list() {
		return this.repository.findAll();
	}

}
